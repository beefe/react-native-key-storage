# react-native-key-storage
the android nativeModule key-storage for react-native 

## Installation and How to use

#### Step 1 - NPM Install

```shell
npm install --save react-native-key-storage
```
#### Step 2 - Update Gradle Settings

```gradle
// file: android/settings.gradle
...

include ':reactkeystorage', ':app' 
project(':reactkeystorage').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-key-storage')
 // if there are more library
 // include ':app' , ':libraryone' , ':librarytwo' , 'more...'
 // project(':libraryonename').projectDir = new File(rootProject.projectDir, '../node_modules/libraryonemodule')
 // project(':librarytwoname').projectDir = new File(rootProject.projectDir, '../node_modules/librarytwomodule')
 // more..
```

#### Step 3 - Update app Gradle Build

```gradle
// file: android/app/build.gradle
...

dependencies {
    ...
    compile project(':reactkeystorage')
}
```

#### Step 4 - Register React Package

```java
...
import com.bee.storage.StoragePackage;  // import StoragePackage

public class MainActivity extends ReactActivity {

  ...
  
  /**
     * A list of packages used by the app. If the app uses additional views
     * or modules besides the default ones, add more packages here.
     */
    @Override
    protected List<ReactPackage> getPackages() {
        return Arrays.<ReactPackage>asList(
                new MainReactPackage(),
                new StoragePackage()  // registered StoragePackage
        );
    }
}

```
