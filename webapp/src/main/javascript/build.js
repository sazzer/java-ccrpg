({
    baseUrl: "${basedir}/target/js",
    dir: "${project.build.directory}/${project.build.finalName}/resources/js",
    paths: {
        'jquery': '${basedir}/src/main/javascript/components/jquery/dist/jquery',
        'crossroads': '${basedir}/src/main/javascript/components/crossroads/dist/crossroads',
        'handlebars': '${basedir}/src/main/javascript/components/handlebars/handlebars.amd',
        'hasher': '${basedir}/src/main/javascript/components/hasher/dist/js/hasher',
        'signals': '${basedir}/src/main/javascript/components/js-signals/dist/signals',
        'js-signals': '${basedir}/src/main/javascript/components/js-signals/dist/signals',
        'i18next': '${basedir}/src/main/javascript/components/i18next/i18next.amd.withJQuery'
    },
    shim: {
    },
    optimize: "none"
 })
