angular.module("ScenariosApp", ['pascalprecht.translate', 'ngSanitize'])
.config(["$translateProvider", function($translateProvider) {
    $translateProvider.useLoader('$translatePartialLoader', {
        urlTemplate: '{part}_{lang}.json'
    });
    $translateProvider.useSanitizeValueStrategy('escape');
}])
.factory('ScenariosServices',ScenariosServices)
.controller("ScenariosCtrl",
    function($scope, $rootScope, $translate, $translatePartialLoader, $sce, $timeout, $interval, $http, ScenariosServices) {
	
		$scope.language = "en";
		$translatePartialLoader.addPart("../i18n/lang");
	    $translate.refresh();
	    $translate.use($scope.language);
	    
	    $scope.runScenarioId=0;
        
		$scope.getScenarios = function () {
			ScenariosServices.getScenarios($scope.language).then(
		    function (response) {
		        if (response.status == 200) {
		            $scope.scenarios = response.data;
		        }
		    });
		};
		$scope.getScenarios($scope.language);
		
		$scope.getScenario = function (scenarioId) {
			return ScenariosServices.getScenario(scenarioId);
		};
		
		$scope.changeLang = function () {
			$translate.use($scope.language);
			$scope.getScenarios($scope.language);
		};
		
		$scope.runScenario = function () {
			return ScenariosServices.runScenario($scope.runScenarioId);
        };

});
		

		
