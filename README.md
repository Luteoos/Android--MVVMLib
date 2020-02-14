# Android--MVVMLib with LiveData
Base classes for easier handling MVVM in android/Kotlin

Contains 
- android.arch.lifecycle:extensions:2.2.0
- androidx.appcompat:appcompat:1.1.0

```
 maven { url 'https://jitpack.io' }
 implementation 'com.github.Luteoos:Android--MVVMLib:4.0.0'
```

### Library implements simple 'event bus' between VM and View able to transfer Event<Int> to listening classes

```kotlin
override fun onVMMessage(msg: Event<Int>){
	when(msg.peek())
 ...
}
```

- after initialization viewModel invoke `connectToVMMessage()`
- using `Event<T>.get()` returns `null` after one use, use `peek()` for multiple uses 

### changes in 4.0.0
- added `Event<T>`
- added `BaseActivityWithoutVM`
- updated appcompat and lifecycle-extensions
- simplified unused imports
