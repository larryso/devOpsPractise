{
    helm: {
        images:{
            register: $.variables.containerRegistry,
            repository: $.variables.containerRegistry + '/' + $.helm.nameOverride,
        },
        nameOverride: 'demo', 
        ingress: {
            enabled: 'true',
            tls: {
                secretName: 'ingress-tls-cert',
            },
            class: 'nginx-ingress-public',
        },
        app: {
            ENV1: 'env1', 
            ENV2: 'env2', 
            ENV3: 'env3', 
            ENV4: 'env4', 
        },
    },
    variables: {
        midRegion: 'Shanghai', 
        containerRegistry: 'docker-hub' + $.variables.midRegion +'.cr.aliyun.cmo/demo',
    },
}