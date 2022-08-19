# **05: LiveData**

## Overview

`LiveData` is an **observable** data holder class that is lifecycle aware, which means it respects the lifecycle of other application components such as **activities**, **fragments** & **services**. This ensures `LiveData` only updates application component **observers** that are in an active lifecycle state.

`LiveData` considers an **observer** to be in an active state, i.e., its lifecycle is in the **started** or **resumed** state. `LiveData` only notifies active **observers** about updates. Inactive **observers** registered to watch `LiveData` objects are not notified about changes.

You can register an **observer** paired with an object that implements the `LifecycleOwner` interface. This allows the **observer** to be removed when the `Lifecycle` object's state changes to **destroyed**. This is useful for **activities** & **fragments** because they safely **observe** `LiveData` objects & not worry about memory leaks as they are instantly unsubscribed when their lifecycles are **destroyed**.

## Why LiveData?

Here some advantages of using `LiveData`:

- `LiveData` notifies the `Observer` objects when underlying data changes. You can update the UI in these `Observer` objects. This way, you do not need to update the UI every time the application data changes because the **observer** handles it for you.
- `Observers` are bound to `Lifecycle` objects & clean up after themselves when their associated lifecycle is **destroyed**.
- If the **observer's** lifecycle is inactive, then it does not receive any `LiveData` events.
- UI components **observe** relevant data & do not stop or resume **observation**. `LiveData` automatically manages all of this since it is aware of the relevant lifecycle status changes while observing.
- If a lifecycle becomes inactive, it receives the latest data upon becoming active again.
- If a **UI controller** is recreated due to a configuration change, it immediately receives the latest available data.

### HomeViewModel

In the `ViewModel` class, create an instance of `LiveData` to hold a certain type of data.

```kotlin
...

class HomeViewModel : ViewModel() {
    private val _count = MutableLiveData<Int>()
    val count: LiveData<Int> get() = _count 

    init {
        reset()
    }

    fun plusOne() {
        _count.value = _count.value?.plus(1)
    }

    fun reset() {
        _count.value = 0
    }
}
```

`LiveData` has no publicly available methods to update the stored data. The `MutableLiveData` class exposes the `setValue(T)` & `postValue(T)` methods publicly. You must use these if you wish to edit the value stored in a `LiveData` object. The `ViewModel` only exposes immutable `LiveData` objects to the **observers**. 

### HomeFragment

In a **UI controller**, i.e., **activity** or **fragment**, create an `Observer` object that defines the `onChange()` method. This controls what happens when the `LiveData` object's data changes. 

To attach the `Observer` object to the `LiveData` object, use the `observe()` method. The `observe()` method takes a `LifecycleOwner` object. This subscribes the `Observer` object to the `LiveData` object so that it is notified of changes.

When you update the value stored in the `LiveData` object, it triggers all registered **observers** as long as the attached `LifecycleOwner` is in the **active** state.

`LiveData` allows **UI controller** **observers** to subscribe to updates. When the data stored by the `LiveData` object changes, the UI automatically updates in response.

```kotlin
...

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        val tvOutput: TextView = view.findViewById(R.id.tv_count)
        val btnPlusOne: Button = view.findViewById(R.id.btn_plus_one)
        val btnReset: Button = view.findViewById(R.id.btn_reset)

        val viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.count.observe(viewLifecycleOwner) { // Observe any changes to count in HomeViewModel
            tvOutput.text = it.toString() // Update tvOutput when there is a change in value
        }

        btnPlusOne.setOnClickListener {
            viewModel.plusOne()
        }

        btnReset.setOnClickListener {
            viewModel.reset()
        }

        return view
    }
}
```

In most cases, an application component's `onCreate()` method is the best place to start observing a `LiveData` object. This ensures the system does not make redundant calls from a **UI controller's** `onResume()` method. Also, ensures that the **UI controller** has data that it can display as soon as its state becomes active.

## Formative Assessment

No formative assessment today. However, I encourage you to try out the examples above.
