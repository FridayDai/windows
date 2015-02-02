module.exports = function(grunt) {

    grunt.initConfig({
        awsebtdeploy: {
            demo: {
                options: {
                    region: 'us-east-1',
                    applicationName: 'ratchet-v2-admin',
                    environmentCNAME: 'ratchet-v2-admin-qa.elasticbeanstalk.com',
                    sourceBundle: "./target/ratchet-v2-admin-portal-0.1.war",
                    // or via the AWS_ACCESS_KEY_ID environment variable
                    accessKeyId: "AKIAIWTB37MOKO6FLJEA",
                    // or via the AWS_SECRET_ACCESS_KEY environment variable
                    secretAccessKey: "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",
                    versionLabel: '' + new Date().getTime(),
                    s3: {
                        bucket: 'elasticbeanstalk-us-east-1-552836082491'
                    }
                }
            }
        }
    });

    grunt.loadNpmTasks('grunt-awsebtdeploy');

    grunt.registerTask('upload', ['awsebtdeploy']);

};
