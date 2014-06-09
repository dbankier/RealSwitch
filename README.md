# RealSwitch for Android

A native Android Titanium module for a **real** Switch for Android - not a Toggle or Checkbox - an `android.widget.Switch;`

![switch](http://developer.android.com/images/ui/switch.png)

**Note**: API 14 and above.

## Install

The modules/zip files is in the `dist` folder of the repository. Add it to your project like you would any other native module.

Require the module with the following code:

~~~
var RealSwitch = require("com.yydigital.realswitch");
~~~


## Usage

RealSwitch uses the same API as `Ti.UI.Switch`.

Here is an example usage:

~~~
var realSwitch = RealSwitch.createRealSwitch({
  value:true
});
win.add(realSwitch);

realSwitch.addEventListener('change',function(e){
  Ti.API.info('Switch value: ' + basicSwitch.value);
});
~~~


###Licence: MIT
