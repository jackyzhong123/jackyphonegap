cordova.define("de.websector.myplugin.MyPlugin", function (require, exports, module) {
    var exec = require('cordova/exec');
    /**
     * Constructor
     */
    function MyPlugin() { };

    MyPlugin.prototype.sayHello = function () {
        exec(function (result) {
            // result handler
            alert(result);
        },
          function (error) {
              // error handler
              alert("Error" + error);
          },
          "MyPlugin",
          "sayHello",
          []
        );
    };

    MyPlugin.prototype.encodeByDES = function (arg1) {
      //  console.log("encode")
        var deferred = $.Deferred();
        //console.log("encode2");
        exec(function (result, prevalue) {
            
            deferred.resolve(result, prevalue);
        },
          function (error) {
              // error handler
              alert("Error" + error);
          },
          "MyPlugin",
          "encodeByDES",
          [arg1]
        );
        //console.log("endencode")
        return deferred.promise();
    };





    MyPlugin.prototype.setValueToLocal = function (key, value) {
        var deferred = $.Deferred();
        exec(function (key,value) {
            deferred.resolve(key, value);
        },
        function (error) {
            // error handler
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
        exec(function (key, value) {
            deferred.resolve(key, value);
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

    MyPlugin.prototype.jumpToXGCQ = function (arg1,arg2) {
        //  var deferred = $.Deferred();
        exec(function () {
            // deferred.resolve(result);
        },
        function (error) {
            // error handler
            alert("Error" + error);
        },
         "MyPlugin",
          "jumpToXGCQ",
          [arg1, arg2]
        );
        //  return deferred.promise();
    };

    MyPlugin.prototype.jumpToBack = function () {
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

    MyPlugin.prototype.jumpToURL = function (arg, isSys) {
        exec(function () {
            // deferred.resolve(result);
        },
      function (error) {
          alert("Error" + error);
      },
       "MyPlugin",
        "jumpToURL",
        [arg, isSys]
      );

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
});
