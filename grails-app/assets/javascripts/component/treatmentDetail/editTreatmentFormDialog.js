var flight = require('flight');
var withDialog = require('../common/withDialog');
var withForm = require('../common/withForm');

function editTreatmentFormDialog() {
    /* jshint validthis:true */

    this.attributes({
        submitBtnSelector: '.update-btn',

        treatmentTitleFieldSelector: '#edit-treatment-title',
        templateTitleFieldSelector: '#edit-treatment-tmpTitle',
        surgeryTimeRequiredFieldSelector: '#edit-treatment-surgeryTimeRequired',
        descriptionFieldSelector: '#edit-treatment-description'
    });

    this.onShow = function (e, data) {
        this.select('treatmentTitleFieldSelector').val(data.treatmentTitle);
        this.select('templateTitleFieldSelector').val(data.templateTitle);
        this.select('surgeryTimeRequiredFieldSelector').val(data.surgeryTimeRequire === 'Yes' ? 'true' : 'false');
        this.select('descriptionFieldSelector').val(data.description);
    };

    this.onFormSuccess = function (e, data) {
        this.trigger('editTreatmentSuccess', {
            treatmentTitle: data.title,
            templateTitle: data.tmpTitle,
            surgeryTimeRequire: data.surgeryTimeRequired === 'true' ? 'Yes' : 'No',
            description: data.description
        });

        this.hideDialog();
    };

    this.after('initialize', function () {
        this.on(document, 'showEditTreatmentFormDialog', this.onShow);

        this.on('formSuccess', this.onFormSuccess);
    });
}

module.exports = flight.component(withDialog, withForm, editTreatmentFormDialog);
