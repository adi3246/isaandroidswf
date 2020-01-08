package com.airasia.swf.postsList

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.tribehired.swf.R
import com.tribehired.swf.databinding.ActivityPostsListBinding
import kotlinx.android.synthetic.main.toolbar.view.*
import java.util.*


class PostsListActivity : AppCompatActivity() {
    lateinit var viewModel: NotificationViewModel
    lateinit var activityBinding: ActivityPostsListBinding
    lateinit var broadcastReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_posts_list)
        activityBinding.toolbar.toolbar_title.text = resources.getString(R.string.notification).toUpperCase()
        viewModel = ViewModelProviders.of(this).get(NotificationViewModel::class.java)
        activityBinding.viewModel = viewModel
        if(viewModel.notificationForm.transporterMode > 2){
            (activityBinding.toolbar as Toolbar).navigationIcon?.setColorFilter(resources.getColor(R.color.white), PorterDuff.Mode.SRC_ATOP)
            activityBinding.toolbar.setBackgroundColor(resources.getColor(R.color.tp_gray))
        }
        if (savedInstanceState == null) {}

        setSupportActionBar(activityBinding.toolbar as Toolbar)
        (activityBinding.toolbar as Toolbar).setNavigationOnClickListener { onBackPressed() }

        activityBinding.rvNotification.adapter =
            NotificationAdapter(
                this,
                object :
                    NotificationAdapter.OnItemClickListener {
                    override fun onItemClick(id: Int, position: Int) {
                        viewModel.notificationId = id

                        startActivityForResult(notificationDetailIntent(viewModel.notificationForm.notificationList[position]), ApplicationConstants.REQUEST_NOTIFICATION_DETAIL)

                        Timer().schedule(object : TimerTask() {
                            override fun run() {
                                runOnUiThread {
                                    viewModel.readNotification(id)
                                    (activityBinding.rvNotification.adapter as NotificationAdapter).changeBgColor(
                                        id,
                                        position
                                    )
                                }
                            }
                        }, 400)
                    }
                })

        activityBinding.scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (activityBinding.scrollView.getChildAt(activityBinding.scrollView.childCount - 1) != null) {
                if ((scrollY >= (activityBinding.scrollView.getChildAt(activityBinding.scrollView.childCount - 1).measuredHeight - activityBinding.scrollView.measuredHeight)) &&
                    scrollY > oldScrollY) {
                    if (!viewModel.loadMoreInProgress && viewModel.notificationForm.hasMoreData){
                        viewModel.getNotification(false, true)
                    }
                }
            }
        })

        setupObserver()

        viewModel.getNotification(false, false)

        activityBinding.pullToRefresh.setColorSchemeColors(getColor(R.color.dodoo_blue), getColor(R.color.dodoo_blue), getColor(R.color.dodoo_blue))
        activityBinding.pullToRefresh.setOnRefreshListener {
            viewModel.clearData()
            viewModel.getNotification(true, false)
        }

        setupBroadCastReceiver()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ApplicationConstants.REQUEST_NOTIFICATION_DETAIL && resultCode == Activity.RESULT_OK) {
            (activityBinding.rvNotification.adapter as NotificationAdapter).removeItem(viewModel.notificationId)
        }
    }

    override fun onBackPressed() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver)

        val output = Intent()
        output.putExtra(ApplicationConstants.TOTAL_NOTIFICATION, (activityBinding.rvNotification.adapter as NotificationAdapter).getTotalUnreadInbox())
        setResult(RESULT_OK, output)

        super.onBackPressed()
    }

    private fun setupBroadCastReceiver(){
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                if (intent.action == ApplicationConstants.PUSH_NOTIFICATION){
                    viewModel.getNotification(false, false)
                }
            }
        }

        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, IntentFilter(ApplicationConstants.PUSH_NOTIFICATION))
    }

    private fun setupObserver(){
        viewModel.statusMessage.observe(this, Observer<String> {
            if(it != "")
                showAlertDialog(it, null)
            else
                activityBinding.pullToRefresh.isRefreshing = false

        })

        viewModel.updateNotificationListAdapter.observe(this, Observer<Boolean> {
            (activityBinding.rvNotification.adapter as NotificationAdapter).updateItemAdapter(viewModel.notificationTempModels)
        })
    }
}

fun Context.postsListIntent(): Intent {
    return Intent(this, PostsListActivity::class.java)
}