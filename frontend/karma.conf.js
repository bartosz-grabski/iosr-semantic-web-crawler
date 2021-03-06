// Karma configuration
// Generated on Sun May 17 2015 19:18:00 GMT+0200 (CEST)

module.exports = function(config) {
    config.set({

        // base path that will be used to resolve all patterns (eg. files, exclude)
        basePath: '',


        // frameworks to use
        // available frameworks: https://npmjs.org/browse/keyword/karma-adapter
        frameworks: ['mocha', 'chai'],


        // list of files / patterns to load in the browser
        files: [
            'lib/static/lib/angular.js',
            'lib/static/lib/angular-route.js',
            'node_modules/angular-mocks/angular-mocks.js',
            'lib/static/lib/jquery-1.10.1.min.js',
            'lib/static/lib/jquery-ui.js',
            'lib/static/lib/bootstrap.min.js',
            'lib/static/lib/ui-bootstrap-tpls-0.11.0.js',
            'lib/static/js/services/services.js',
            'lib/static/js/services/*.js',
            'lib/static/js/controllers/controllers.js',
            'lib/static/js/controllers/*.js',
            'lib/static/js/**/*.js',
            'test/**/*Spec.js'
        ],


        // list of files to exclude
        exclude: [
        ],


        // preprocess matching files before serving them to the browser
        // available preprocessors: https://npmjs.org/browse/keyword/karma-preprocessor
        preprocessors: {
            'lib/static/js/services/**/*.js': ['coverage']
        },


        // test results reporter to use
        // possible values: 'dots', 'progress'
        // available reporters: https://npmjs.org/browse/keyword/karma-reporter
        reporters: ['progress', 'dots', 'junit','coverage'],

        coverageReporter: {
            type: 'cobertura', 
            dir: './coverage'
        },


        // web server port
        port: 9876,


        // enable / disable colors in the output (reporters and logs)
        colors: true,


        // level of logging
        // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
        logLevel: config.LOG_INFO,


        // enable / disable watching file and executing tests whenever any file changes
        autoWatch: false,


        // start these browsers
        // available browser launchers: https://npmjs.org/browse/keyword/karma-launcher
        browsers: ['PhantomJS'],


        // Continuous Integration mode
        // if true, Karma captures browsers, runs the tests and exits
        singleRun: true,
        junitReporter: {
            outputFile: 'test-results.xml'
        }
    });
};
