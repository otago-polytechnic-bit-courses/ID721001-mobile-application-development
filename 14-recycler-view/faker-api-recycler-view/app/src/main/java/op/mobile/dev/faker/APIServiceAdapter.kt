package op.mobile.dev.faker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import op.mobile.dev.faker.databinding.RecyclerViewItemBinding

class APIServiceAdapter :
    ListAdapter<APIServiceProperty, APIServiceAdapter.APIServicePropertyViewHolder>(DiffCallback) {

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
            return oldItem.name == newItem.name
        }
    }

    class APIServicePropertyViewHolder(private var binding: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(apiServiceProperty: APIServiceProperty) {
            binding.apiServiceProperty = apiServiceProperty
            binding.executePendingBindings()
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