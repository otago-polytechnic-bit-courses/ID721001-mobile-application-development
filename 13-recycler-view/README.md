# **RecyclerView**

## Overview
`RecyclerView` makes it easy to efficiently display large datasets. All you need to do is provide the data, i.e., API or database & define how each item in the `RecyclerView` should look. The `RecyclerView` library dynamically creates an item when it is needed. 

When an item scrolls off the screens, `RecyclerView` does not destroy its `View`. Instead, `RecyclerView` recycles the `View` for new item that has scrolled onto the screen. This improves performance, responsiveness & reduces power consumption.

## Code Example
Open the `api-recycler-view` directory provided to you in **Android Studio**. 

Lets take a look at what is happening...

### RecyclerViewItem Layout
So far, you have looked at how to bind `ViewModel` data to a layout. The following is an example on how to bind model data, i.e., `GitHubJobs` to a layout.

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <data>

        <variable
            name="githubJobs"
            type="op.mobile.app.dev.api.model.GitHubJobs" />
    </data>

    <androidx.cardview.widget.CardView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginBottom="8dp"
        app:cardCornerRadius="8dp"
        app:cardUseCompatPadding="true"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

        <androidx.constraintlayout.widget.ConstraintLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent">

            <TextView
                android:id="@+id/tv_title"
                android:layout_width="0dp"
                android:layout_height="wrap_content"
                android:layout_marginStart="16dp"
                android:layout_marginTop="16dp"
                android:layout_marginBottom="16dp"
                android:text="@{githubJobs.title}"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintEnd_toEndOf="parent"
                app:layout_constraintStart_toStartOf="parent"
                app:layout_constraintTop_toTopOf="parent" />

        </androidx.constraintlayout.widget.ConstraintLayout>

    </androidx.cardview.widget.CardView>
</layout>
```

### ServiceViewHolder

- `RecyclerView.ViewHolder` - define an item `View` & metadata about its position in the `RecyclerView`.
- `executePendingBindings()` - evaluate pending bindings & updates any `Views` that have expressions bound to variables.

```kotlin
...

class ServiceViewHolder(private var binding: RecyclerViewItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(githubJobs: GitHubJobs) {
        binding.githubJobs = githubJobs
        binding.executePendingBindings()
    }
}
```

### ServiceAdapter
- `ListAdapter` - base class for display list data in a `RecyclerView`.
- `DiffUtil.ItemCallback` -  a `DiffUtil.Callback` does list indexing & item diffing. `DiffUtil.ItemCallback` only does diffing between two non-null items in a list.
  - `areItemsTheSame()` - check whether two objects represent the same item.
  - `areContentsTheSame()` - check whether two items have the same data. 
- `onCreateViewHolder()` - create a new `RecyclerView.ViewHolder`
- `onBindViewHolder` - update the `RecyclerView.ViewHolder` contents with the item at the given `position`. 

```kotlin
...

class ServiceAdapter :
    ListAdapter<GitHubJobs, ServiceViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<GitHubJobs>() {
        override fun areItemsTheSame(
            oldItem: GitHubJobs,
            newItem: GitHubJobs
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GitHubJobs,
            newItem: GitHubJobs
        ): Boolean {
            return oldItem == newItem
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
```

### ServiceBindingAdapter

- `submitList()` - submit a list to be diffed & displayed.

This **BindingAdapter** will be used in `fragment_github_jobs.xml`.

```kotlin
...

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<GitHubJobs>?) {
    val adapter = recyclerView.adapter as ServiceAdapter
    adapter.submitList(data)
}

...
```

### GitHubFragment Layout

- Declare a `RecyclerView` with a `LinearLayoutManager`. This is used for displaying items vertically or horizontally. 
- Set `listData` attribute to `LiveData<List<GitHubJobs>>`.
- Set the item's layout to `recycler_view_item.xml`.

```xml
...

<androidx.recyclerview.widget.RecyclerView
    android:id="@+id/rv_jobs"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_marginStart="16dp"
    android:layout_marginTop="16dp"
    android:layout_marginEnd="16dp"
    android:layout_marginBottom="16dp"
    app:layoutManager="androidx.recyclerview.widget.GridLayoutManager"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintTop_toTopOf="parent"
    app:listData="@{githubJobsViewModel.response}"
    tools:listitem="@layout/recycler_view_item" />

...
```

### GitHubFragment
Bind the `RecyclerView` adapter to `ServiceAdapter`.

```kotlin
...

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

        binding.rvJobs.adapter = ServiceAdapter()

        return binding.root
    }
}
```
