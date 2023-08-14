# build
custom_build(
    ref = 'cnsa-catalog-service',
    command = './gradlew bootBuildImage --imageName $EXPECTED_REF',
    deps = ['build.gradle', 'src']
)

# deploy
k8s_yaml(['kubernetes/deployment.yaml', 'kubernetes/service.yaml'])

# manage
k8s_resource('cnsa-catalog-service', port_forwards=['9001'])
