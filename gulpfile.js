var path = require('path'),
    gulp = require('gulp'),
    webpack = require('gulp-webpack-build');

var paths = {
    sass: ['./grails-app/assets/stylesheets/**/*.scss'],
    js: [
        './grails-app/assets/javascripts/**/*.js',
        '!./grails-app/assets/javascripts/lib/**/*.js',
        '!./grails-app/assets/javascripts/bundles/**/*.js',
        '!./grails-app/assets/javascripts/bower_components/**/*.js'
    ],
    webpack: {
        src: './grails-app/assets/javascripts',
        dest: './grails-app/assets/javascripts/bundles'
    }
};

// JS hint for checking JS code
var jshint = require('gulp-jshint');
var jshintStylish = require('jshint-stylish');
var jshintConfigPath = '.jshintrc';

gulp.task('js-lint', function () {
    return gulp.src(paths.js)
        .pipe(jshint(jshintConfigPath))
        .pipe(jshint.reporter(jshintStylish))
        .pipe(jshint.reporter('fail'));
});

// Scss lint for checking JS code
var scsslint = require('gulp-scss-lint');

gulp.task('scss-lint', function() {
    return gulp.src(paths.sass)
        .pipe(scsslint());
});

// This lint task should be run before commit code.
gulp.task('lint', ['js-lint', 'scss-lint']);

var webpackOptions = {
        debug: true,
        watchDelay: 200
    },
    webpackConfig = {
        useMemoryFs: true,
        progress: true
    },
    WEBPACK_CONFIG_FILENAME = webpack.config.CONFIG_FILENAME;

gulp.task('webpack', [], function() {
    return gulp.src(path.join(WEBPACK_CONFIG_FILENAME))
        .pipe(webpack.init(webpackConfig))
        .pipe(webpack.props(webpackOptions))
        .pipe(webpack.run())
        .pipe(webpack.format({
            version: false,
            timings: true
        }))
        .pipe(webpack.failAfter({
            errors: true,
            warnings: true
        }))
        .pipe(gulp.dest('.'));
});

gulp.task('watch', function() {
    gulp.watch(paths.js).on('change', function(event) {
        if (event.type === 'changed') {
            gulp.src(event.path, { base: path.resolve(paths.webpack.src) })
                .pipe(webpack.closest(WEBPACK_CONFIG_FILENAME))
                .pipe(webpack.init(webpackConfig))
                .pipe(webpack.props(webpackOptions))
                .pipe(webpack.watch(function(err, stats) {
                    gulp.src(this.path, { base: this.base })
                        .pipe(webpack.proxy(err, stats))
                        .pipe(webpack.format({
                            verbose: true,
                            version: false
                        }))
                        .pipe(gulp.dest(this.base));
                }));
        }
    });
});
