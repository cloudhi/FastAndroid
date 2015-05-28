# Fast Android

To create Android App fast.

## Dependencies

> compile 'com.github.yoojia:fast:1.2@aar'

## Note

### - Proguard

    -keepclassmembers class * {
        @com.github.yoojia.fast.view.AutoView *;
    }
    
### - TableView Style

#### Common

- `:first="true"`
- `:last="true"`
- `:textGravity="left|right"`

#### InputFieldView

- `android:inputType`
- `android:hint`
- `android:maxLength`
- `android:text`
- `android:enabled`


#### StatucTextFieldView

- `:textLabel`
- `android:text`

#### TableTextFieldView

- `:cellIcon`
- `:cellLabel`
- `:cellValue`
- `:cellDisabledNext`
    