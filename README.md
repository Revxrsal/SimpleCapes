[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
# SimpleCapes
A MinecraftForge mod which adds capes to the player

## Explanation
The mod basically has 3 modes:
* **URL mode**: It will get the cape image from the URL. To switch to this mode, **/simplecapes url \<cape url>** is used.
* **Local mode**: It will take the cape image from your computer. The image must be in **.minecraft/simplecapes/** directory. To switch to this mode, **/simplecapes local \<image name>**. Don't forget to add the extension (.png, .jpg, etc.)
* **Clipboard mode**: It will take the cape image from your current clipboard. To use this mode, **/simplecapes clipboard** is used. Note that, when you use this mode, the image is saved in **.minecraft/simplecapes/clipboard.png**, and will be used later, and only changes when you use the command with another clipboard image.

## Commands
Command: **/simplecapes**. Alias: **/sc**
* /sc url <url> - Sets the cape image from the given URL
* /sc local \<image name> - Sets the cape image from your machine. The image **must** be in **.minecraft/simplecapes/**. Adding the extension of the image is also required. Refer to the explanation section for more details
* /sc clipboard - Sets the cape image from your current image clipboard.
