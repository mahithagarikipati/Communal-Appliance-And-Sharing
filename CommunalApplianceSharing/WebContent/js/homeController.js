/**
 * 
 */

 var home= angular.module("home", ["ngMessages","ngMaterial","ngAria"]);

 home.controller("homeController", [ '$scope', '$http','$window','$mdDialog', function($scope, $http,$window,$mdDialog) {
	        
}]);
function addFields(){
            //declare gettingData()method
			var number = 10;  //get amount of users from LenderInstance with same zip code
            var container = document.getElementById("container");
            while (container.hasChildNodes()) {
                container.removeChild(container.lastChild);
            }
            for (i=0;i<number;i++){
               // container.appendChild(document.createTextNode("Member " + (i+1)));
                var input = document.createElement("input");
                input.type = "text";
				var button = document.createElement("BUTTON");
				button.innerHTML = "borrow";
				container.appendChild(input);
				container.appendChild(button);
                container.appendChild(document.createElement("br"));
				
            }
        }
//how to initialize button in JS
	//connect gettingData to java file