package op.mobile.app.dev.api.service

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import op.mobile.app.dev.api.databinding.RecyclerViewItemBinding
import op.mobile.app.dev.api.model.GitHubJobs

class ServiceAdapter :
    ListAdapter<GitHubJobs, ServiceViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<GitHubJobs>() {
        override fun areItemsTheSame(
            oldItem: GitHubJobs,
            newItem: GitHubJobs
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: GitHubJobs,
            newItem: GitHubJobs
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ServiceViewHolder {
        return ServiceViewHolder(
            RecyclerViewItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(
        holder: ServiceViewHolder,
        position: Int
    ) {
        val githubJob = getItem(position)
        holder.bind(githubJob)
    }
}