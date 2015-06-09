module.exports = function(grunt) {

    grunt.loadNpmTasks('grunt-karma');
    grunt.loadNpmTasks('grunt-forever');

    grunt.initConfig({

        karma: {
            unit: {
                configFile: 'karma.conf.js'
            }
        },
        forever: {
            app: {
                options: {
                    index: 'lib/app.js',
                    logDir: 'logs'
                }
            }
        }

    });

   grunt.registerTask('start', ['karma','forever:app:start']);
   grunt.registerTask('test', ['karma']);
};
