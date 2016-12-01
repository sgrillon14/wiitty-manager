function ScenariosServices($http, $rootScope) {
	return {
		getScenarios : function(lang) {
			return $http({
				method : "get",
				url : "http://localhost:8089/wiitty/api/scenarios?lang=" + lang
			});
		},
		getScenario : function(scenarioId) {
			return "http://localhost:8089/wiitty/api/scenario/" + scenarioId;
		},
		runScenario : function(scenarioId) {
			return $http({
				method : "get",
				url : "http://localhost:8089/wiitty/api/run/scenario/" + scenarioId
			});
		}
	};
};