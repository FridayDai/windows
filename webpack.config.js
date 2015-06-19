var webpack = require("webpack");

var contextUrl = "grails-app/assets/javascripts";
var absoluteContext = __dirname + "/" + contextUrl;

module.exports = {
    context: contextUrl,
    entry: {
        clients: "./pages/clients.js",
        clientDetail: "./pages/clientDetail.js"
    },
    output: {
        path: absoluteContext,
        filename: "./bundles/[name].bundle.js"
    },
    resolve: {
        root: absoluteContext,
        alias: {
            jquery: "bower_components/jquery/dist/jquery.js",
            lodash: "bower_components/lodash/lodash.js",
            flight: "bower_components/flight/index.js",
            bootstrap: "bower_components/bootstrap/dist/js/bootstrap.js",
            jValidate: "bower_components/jquery-validation/dist/jquery.validate.js",
            jForm: "bower_components/jquery-form/jquery.form.js",
            dataTable: "bower_components/DataTables/media/js/jquery.dataTables.js",
            moment: "bower_components/moment/min/moment.min.js",
            momentTZ: "bower_components/moment-timezone/builds/moment-timezone-with-data.js"
        }
    },
    module: {
        noParse: [
        ]
    },
    plugins: [
        new webpack.ProvidePlugin({
            $: "jquery",
            jQuery: "jquery",
            "window.jQuery": "jquery",
            "root.jQuery": "jquery",
            _: "lodash",
            moment: 'moment'
        })
    ]
};