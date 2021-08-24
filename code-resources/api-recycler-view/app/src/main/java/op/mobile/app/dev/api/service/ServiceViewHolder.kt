package op.mobile.app.dev.api.service

import androidx.recyclerview.widget.RecyclerView
import op.mobile.app.dev.api.databinding.RecyclerViewItemBinding
import op.mobile.app.dev.api.model.GitHubJobs

class ServiceViewHolder(private var binding: RecyclerViewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(githubJobs: GitHubJobs) {
        binding.githubJobs = githubJobs
        binding.executePendingBindings()
    }
}