package op.mobile.app.dev.graysono.travelling.helpers.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import op.mobile.app.dev.graysono.travelling.databinding.RecyclerViewCountryItemBinding
import op.mobile.app.dev.graysono.travelling.helpers.IOnClickListener
import op.mobile.app.dev.graysono.travelling.model.Country

class CountryRVAdapter(private val listener: IOnClickListener) :
    ListAdapter<Country, CountryRVAdapter.CountryRVViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(
            oldItem: Country,
            newItem: Country
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Country,
            newItem: Country
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryRVViewHolder {
        return CountryRVViewHolder(
            RecyclerViewCountryItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(
        holder: CountryRVViewHolder,
        position: Int
    ) {
        val country = getItem(position)
        holder.bind(country)
    }

    inner class CountryRVViewHolder(private var binding: RecyclerViewCountryItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(country: Country) {
            binding.country = country
            binding.executePendingBindings()
        }

        override fun onClick(view: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION)
                listener.onItemClick(position)
        }
    }
}
