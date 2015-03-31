 
    var exec = require('cordova/exec');
    /**
     * Constructor1
     */
    function MyPlugin() { };

    MyPlugin.prototype.sayHello = function (arg) {
        exec(function (result) { 
            alert(result);
        },
          function (error) {
              // error handler
              alert("Error" + error);
          },
          "MyPlugin",
          "sayHello",
          [arg]
        );
    };

    MyPlugin.prototype.showMenuTabs = function (arg) {
        exec(function ( ) {
           // alert(result);
        },
          function (error) {
              // error handler
              alert("Error" + error);
          },
          "MyPlugin",
          "showMenuTabs",
          [arg]
        );
    };
    MyPlugin.prototype.injectDetailWebView = function (arg) {
        exec(function ( ) {
           // alert(result);
        },
          function (error) {
              // error handler
              alert("Error" + error);
          },
          "MyPlugin",
          "injectDetailWebView",
          [arg]
        );
    };
    MyPlugin.prototype.encodeByDES = function (arg1) { 
        var deferred = $.Deferred(); 
        exec(function (result) { 
            deferred.resolve(result);
        },
          function (error) { 
              alert("Error" + error);
          },
          "MyPlugin",
          "encodeByDES",
          [arg1]
        ); 
        return deferred.promise();
    };





    MyPlugin.prototype.setValueToLocal = function (key, value) {
        var deferred = $.Deferred();
        exec(function (result) {
            deferred.resolve(result);
        },
        function (error) { 
            alert("Error" + error);
        },
         "MyPlugin",
          "setValueToLocal",
          [key, value]
        );
        return deferred.promise();
    };




    MyPlugin.prototype.getValueToLocal = function (key) {
        var deferred = $.Deferred();
        exec(function (result) {
            deferred.resolve(result);
        },
        function (error) {
            // error handler
            alert("Error" + error);
        },
         "MyPlugin",
         "getValueToLocal",
          [key]
        );
        return deferred.promise();

    };


    MyPlugin.prototype.checkCacheExisit = function (source) {
        var deferred = $.Deferred();
        exec(function (result) {
            deferred.resolve(result);
        },
        function (error) {
            // error handler
            // alert("Error" + error);
        },
         "MyPlugin",
          "checkCacheExisit",
         source
        );
        return deferred.promise();
    }




    MyPlugin.prototype.jumpToMainTab = function () {
      //  var deferred = $.Deferred();
        exec(function ( ) {
          // deferred.resolve(result);
        },
        function (error) {
            // error handler
            alert("Error" + error);
        },
         "MyPlugin",
          "jumpToMainTab",
          []
        );
      //  return deferred.promise();
    };

    MyPlugin.prototype.jumpToInternalCordova = function (url,data,needResult) {  
        exec(function () {
            // deferred.resolve(result);
        },
        function (error) {
            // error handler
            alert("Error" + error);
        },
         "MyPlugin",
          "jumpToInternalCordova",
          [url, data,needResult]
        );
        //  return deferred.promise();
    };

    MyPlugin.prototype.jumpToInternalCordovaWithDetail = function (url, data, needResult) {
        exec(function () {            
        },
        function (error) {
            // error handler
            alert("Error" + error);
        },
         "MyPlugin",
          "jumpToInternalCordovaWithDetail",
          [url, data,needResult]
        );
        //  return deferred.promise();
    };
	 
	 MyPlugin.prototype.clearimagecache = function () {
          var deferred = $.Deferred();
        exec(function (result) {
             deferred.resolve(result);
        },
        function (error) {
            // error handler
            alert("Error" + error);
        },
         "MyPlugin",
          "clearimagecache",
          []
        );
        return deferred.promise();
    };
	
	
	

    MyPlugin.prototype.jumpToBack = function (needResult) {
        //  var deferred = $.Deferred();
        exec(function () {
            // deferred.resolve(result);
        },
        function (error) {
            // error handler
            alert("Error" + error);
        },
         "MyPlugin",
          "jumpToBack",
          [needResult]
        );
        //  return deferred.promise();
    };
	
	MyPlugin.prototype.jumpToBackResult = function () {
        //  var deferred = $.Deferred();
        exec(function () {
            // deferred.resolve(result);
        },
        function (error) {
            // error handler
            alert("Error" + error);
        },
         "MyPlugin",
          "jumpToBackResult",
          []
        );
        //  return deferred.promise();
    };
	
	

    MyPlugin.prototype.clearAccount = function () {
        var deferred = $.Deferred();
        exec(function () {
            deferred.resolve(result);
        },
        function (error) {
            alert("Error" + error);
        },
         "MyPlugin",
          "clearAccount",
          []
        );
        return deferred.promise();
    };

    MyPlugin.prototype.jumpToURL = function (arg) {
        if (arg.indexOf("http://jacky.chinacloudsites.cn")==0 || arg.indexOf("http://www.huodongyou.cn")==0)
        {
            $.ajax({
                url: global.serverUrl + "api/Login/MobielWebLogin",
                type: "POST",
                headers: { 'Authorization': 'Bearer ' + localStorage.access_token },
                success: function (data) {
                    var url = "http://jacky.chinacloudsites.cn/Account/inMobileLogin?id=" + data + "&returnUrl=" + encodeURIComponent(arg);
                    exec(function () {
                        // deferred.resolve(result);
                    },
    function (error) {
        alert("Error" + error);
    },
     "MyPlugin",
      "jumpToURL",
      [url]
    );
                }
            });
        }else
        {
            exec(function () {
                // deferred.resolve(result);
            },
function (error) {
    alert("Error" + error);
},
 "MyPlugin",
  "jumpToURL",
  [arg]
);
        }
      

    };

    MyPlugin.prototype.downloadFileAsync = function (remoteURL, filePath) {
        exec(function () {
            // deferred.resolve(result);
        },
      function (error) {
          alert("Error" + error);
      },
       "MyPlugin",
        "downloadFileAsync",
        [remoteURL, filePath]
      );

    };
    MyPlugin.prototype.computeCacheSize = function () {
        var deferred = $.Deferred();
        exec(function (result) {
            deferred.resolve(result);
        },
    function (error) {
        alert("Error" + error);
    },
     "MyPlugin",
      "computeCacheSize",
      []
    );
        return deferred.promise();
    };



    var myPlugin = new MyPlugin();
    module.exports = myPlugin;
 
