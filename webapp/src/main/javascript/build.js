({
    baseUrl: "${basedir}/target/js",
    dir: "${project.build.directory}/${project.build.finalName}/resources/js",
    paths: {
        'jquery': '${basedir}/src/main/javascript/components/jquery/dist/jquery',
        "bootstrap": "${basedir}/src/main/javascript/components/bootstrap/bootstrap",
        'crossroads': '${basedir}/src/main/javascript/components/crossroads/dist/crossroads',
        'handlebars': '${basedir}/src/main/javascript/components/handlebars/handlebars.amd',
        'hasher': '${basedir}/src/main/javascript/components/hasher/dist/js/hasher',
        'signals': '${basedir}/src/main/javascript/components/js-signals/dist/signals',
        'js-signals': '${basedir}/src/main/javascript/components/js-signals/dist/signals',
        'bluebird': '${basedir}/src/main/javascript/components/bluebird/js/browser/bluebird',
        'i18next': '${basedir}/src/main/javascript/components/i18next/i18next.amd.withJQuery'
    },
    shim: {
        "bootstrap": {
            deps: ["jquery"],
            exports: [
                "$.fn.modal"
            ]
        }
    },
    enforceDefine: true,
    optimize: "none"
 })
