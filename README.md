# Skyeng Dict
This app is a small project made for Skyeng company as a test task.
Uses Skyeng API: https://dictionary.skyeng.ru/doc/api/external
## Features
- Search word translations in dictionary
- See a list of meanings of a found word 
- Supports horizontal orientation
## Used external libraries
- Picasso, for image loading
- Dagger2, for dependency injection
- RxJava2, for asynchronous network requests and data stream implementation
- Mockito, for mocking test instances
## Installation
### Build with Android Studio
1. Create or choose a directory on your computer, in which project will be placed
2. Using terminal (you need a Git to be installed - https://github.com/git-guides/install-git):
```sh
cd /path/to/your/dir
git clone https://github.com/101b1/skyeng_dict.git
```
3. With Android Studio open skyeng_dict directory, cloned from Github
4. Connect real device with USB debugging enabled or choose virtual device
5. Choose Run -> Run 'app' in menu bar
### Install existing apk file
1. Download https://github.com/101b1/skyeng_dict/blob/master/skyeng_dict.apk
2. Transfer .apk file to a device
3. Run installation by opening the file
