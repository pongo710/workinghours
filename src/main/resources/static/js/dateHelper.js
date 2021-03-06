(function () {
    'use strict';

    var factoriesModul;
    try {
        factoriesModul = angular.module('dcc.factories');
    } catch (e) {
        factoriesModul = angular.module('dcc.factories', []);
    }


    factoriesModul.factory('DateHelper', [function () {
        var factory = {};

        factory.getMonthShiftedYear = function (monthShift) {
            var date = new Date();
            date.setMonth(date.getMonth() + monthShift);
            return date.getFullYear();
        };
        factory.getMonthShiftedMonth = function (monthShift) {
            var date = new Date();
            date.setMonth(date.getMonth() + monthShift);
            return date.getMonth() + 1;
        };
        factory.convertDateArrayToObject = function convertDateArrayToObject(dateArray) {
            if (dateArray) {
                return new CustomDate(new Date(dateArray[0], dateArray[1] - 1, dateArray[2], dateArray[3], dateArray[4], 0, 0));
            }
            return null;

        };
        return factory;
    }]);
}());
