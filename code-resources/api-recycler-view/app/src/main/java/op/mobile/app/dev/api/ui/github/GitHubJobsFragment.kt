package op.mobile.app.dev.api.ui.github

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.api.R
import op.mobile.app.dev.api.databinding.FragmentGithubJobsBinding
import op.mobile.app.dev.api.service.ServiceAdapter

class GitHubJobsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentGithubJobsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_github_jobs, container, false
        )

        val viewModel = ViewModelProvider(this).get(GitHubJobsViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.githubJobsViewModel = viewModel

        binding.rvJobs.adapter = ServiceAdapter()

        return binding.root
    }
}