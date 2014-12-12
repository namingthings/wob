function RotationController(scope, http) {
  scope.pageRotation = [];
  scope.currentPageIndex = 0;

  scope.setCurrentPage = function (pageIndex, pageRotation) {
    if (pageRotation.length == 0) return;

    if (pageIndex > pageRotation.length-1) {
      pageIndex = 0;
    }
    var url = pageRotation[pageIndex];

    var newWindow = window.open(url, "rotationWindow", 'fullscreen=yes');
    newWindow.focus();

    return pageIndex;
  };

  scope.nextPage = function() {
    scope.currentPageIndex++;
    scope.currentPageIndex = scope.setCurrentPage(scope.currentPageIndex, scope.pageRotation);
  };

  scope.startPageRotation = function() {
    http.get("/wob/config").success(function (config) {
      console.log("config", config);
      scope.pageRotation = config.pageRotation;
      scope.pageRotationInterval = config.pageRotationInterval ? config.pageRotationInterval : 30000;

      scope.nextPage();

      setInterval(scope.nextPage, scope.pageRotationInterval);
    });
  };
}

angular.module('pageRotation', []).controller('RotationController', ['$scope', '$http', RotationController]);
