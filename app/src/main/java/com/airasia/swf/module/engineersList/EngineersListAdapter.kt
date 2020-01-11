package com.airasia.swf.module.engineersList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airasia.swf.databinding.RowEngineersBinding
import com.airasia.swf.module.engineersList.model.EngineerModel
import com.airasia.swf.utils.BindableAdapter

class EngineersListAdapter constructor(context: Context) : RecyclerView.Adapter<EngineersListAdapter.ViewHolder>(),
    BindableAdapter<EngineerModel> {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    private var mData = emptyList<EngineerModel>()

    private val mContext = context

    override fun setData(items: List<EngineerModel>) {
        mData = items
        notifyDataSetChanged()
    }

    override fun changedPositions(positions: Set<Int>) {
        positions.forEach(this::notifyItemChanged)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bindingView = RowEngineersBinding.inflate(mInflater, parent, false)
        return ViewHolder(bindingView.root, bindingView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position < mData.size) {
            val item = mData[position]
            holder.mBinding.model = item
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return mData.size+1
    }

    inner class ViewHolder constructor(itemView: View, bindingView: RowEngineersBinding) : RecyclerView.ViewHolder(itemView){
        var mBinding: RowEngineersBinding = bindingView
        init {
            mBinding = bindingView
        }

    }
}