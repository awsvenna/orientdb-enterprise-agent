'use strict';

angular.module('webappApp').directive('mkeditor', function ($timeout) {
  return {
    require: '^ngModel',
    link: function (scope, elem, attrs, ngModel) {


      var editor;
      scope.$watch(function () {
        return ngModel.$modelValue;
      }, initialize);

      function initialize(value) {
        if (value) {
          ngModel.$setViewValue(value);

        }
        if (!editor) {
          $(elem[0]).markdown({
            autofocus: false,
            savable: false,
            onShow: function (e) {
              e.setContent(value);
              editor = e;
            },
            onChange: function (e) {
              ngModel.$setViewValue(e.getContent());
            }
          })
        } else {
          editor.setContent(value);
        }

      }
    }

  }
});
