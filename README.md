[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.html)

# Telegram Example

A telegram client for android created using **tdlib** library. 
</br>
This is a modified example using **mvvm** pattern and **clean architecture**, also I use libraries: **hilt**, **navigation components** and **kotlin coroutine**.
I did not add a specific UI, you can use both **XML** and **Jetpack Compose**.
</br></br>
Original repository - [indritbashkimi/TelegramExample](https://github.com/indritbashkimi/TelegramExample)

## To try out this app, you need to: 
* Install the latest of Android Studio
* Download the **tdlib** android library from [tdlib.zip](https://core.telegram.org/tdlib/tdlib.zip) and extract the zip file to the root folder of the project like:
```text
... some files
- app
- data 
- domain 
- libtd
... some files
```
Android studio itself will define this module and add it to other modules, 
in case it doesn't happen you just need to add the **libtd folder** to **application modules**.
* Obtain application identifier hash for Telegram API access at [telegram.org](https://my.telegram.org) 
and store them in the android resources. For example in **data/res/values/telegram_keys.xml**:
```
<resources>
    <integer name="telegram_api_id">your integer api id</integer>
    <string name="telegram_api_hash">your string api hash</string>
</resources>
```

## License
    Copyright (c) 2020 Indrit Bashkimi

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
