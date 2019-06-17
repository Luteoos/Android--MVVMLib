# Android--MVVMLib with LiveData
Base classes for easier handling MVVM in android/Kotlin

Contains android.arch.lifecycle:extensions:1.1.1

```
 maven { url 'https://jitpack.io' }
 implementation 'com.github.Luteoos:Android--MVVMLib:3.0.1'
```

### Library implements simple 'bus' between VM and View able to transfer Int-based parameters, to enable it:

```kotlin
override fun onVMMessage(msg: Int?){
 super.onVMMessage(msg)
 ...
}
```

- after initialization viewModel invoke `connectToVMMessage()`
- `0` is default value assigned, do not use it as your custom parameter value

### changes in 3.0.0
- removed Realm implementation
- removed rxJava implementation
- BaseFragmentMVVM now inherits from BaseFragmentMVVMwithoutVM
