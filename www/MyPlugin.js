 
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

    MyPlugin.prototype.encodeByDES = function (arg1) { 
        var deferred = $.Deferred(); 
        exec(function (result) { 
            deferred.resolve(result);
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
        exec(function (result) {
            deferred.resolve(result);
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

    MyPlugin.prototype.jumpToXGCQ = function (arg1, arg2) {
        var isInternal = "internal";
        var url = "xgcq.html";
        var page = "Home";
        var data = JSON.stringify({ "id": arg1, "name": arg2 });
        exec(function () {
            // deferred.resolve(result);
        },
        function (error) {
            // error handler
            alert("Error" + error);
        },
         "MyPlugin",
          "JumpToView",
          [isInternal,url, page, data]
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
 
