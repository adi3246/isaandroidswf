package com.airasia.swf.module.schedule

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airasia.swf.databinding.RowScheduleBinding
import com.airasia.swf.module.schedule.model.ScheduleListModel
import com.airasia.swf.utils.BindableAdapter


class ScheduleRvAdapter constructor(context: Context) : RecyclerView.Adapter<ScheduleRvAdapter.ViewHolder>(),
    BindableAdapter<ScheduleListModel> {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    private var mData = emptyList<ScheduleListModel>()

    private val mContext = context

    override fun setData(items: List<ScheduleListModel>) {
        mData = items
        notifyDataSetChanged()
    }

    override fun changedPositions(positions: Set<Int>) {
        positions.forEach(this::notifyItemChanged)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bindingView = RowScheduleBinding.inflate((mInflater))
        return ViewHolder(bindingView.root, bindingView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position < mData.size) {
            val item = mData[position]
            holder.mBinding.model = item
            holder.mBinding.index = position+1
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder constructor(itemView: View, bindingView: RowScheduleBinding) : RecyclerView.ViewHolder(itemView){
        var mBinding: RowScheduleBinding = bindingView
        init {
            mBinding = bindingView
        }

    }
}