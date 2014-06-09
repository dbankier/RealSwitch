// This is a test harness for your module
var RealSwitch = require('com.yydigital.realswitch');
var win = Ti.UI.createWindow({
  backgroundColor: 'white'
});

var realSwitch = RealSwitch.createRealSwitch({
  value:true
});
win.add(realSwitch);

realSwitch.addEventListener('change',function(e){
  Ti.API.info('Switch value: ' + basicSwitch.value);
});

win.open();
