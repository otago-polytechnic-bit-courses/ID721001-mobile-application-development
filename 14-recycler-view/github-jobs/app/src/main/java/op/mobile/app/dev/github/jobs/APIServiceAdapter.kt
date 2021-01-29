package op.mobile.app.dev.github.jobs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import op.mobile.app.dev.github.jobs.databinding.RecyclerViewItemBinding

class APIServiceAdapter :
    ListAdapter<APIServiceProperty, APIServicePropertyViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<APIServiceProperty>() {
        override fun areItemsTheSame(
            oldItem: APIServiceProperty,
            newItem: APIServiceProperty
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: APIServiceProperty,
            newItem: APIServiceProperty
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): APIServicePropertyViewHolder {
        return APIServicePropertyViewHolder(
            RecyclerViewItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(
        holder: APIServicePropertyViewHolder,
        position: Int
    ) {
        val apiServiceProperty = getItem(position)
        holder.bind(apiServiceProperty)
    }
}