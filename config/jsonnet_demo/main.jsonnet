local commonDefaultConfig = import 'pub_default.libsonnet';

local controlledDefaultConfig = import 'controlled/pub_default.libsonnet';

local uncontrolledDefaultConfig = import 'uncontrolled/pub_default.libsonnet';

local controlledSpecificConfig = {
    controlled_prod: import 'controlled/pub_prod.libsonnet'
};

local unControlledSpecificConfig = {
    uncontrolled_dev: import 'uncontrolled/pub_dev.libsonnet'
};

local configControlled(env) = commonDefaultConfig + controlledDefaultConfig + controlledSpecificConfig[env];

local configUncontrolled(env) = commonDefaultConfig + uncontrolledDefaultConfig + unControlledSpecificConfig[env];

{
    'controlled/pub_prod.json': configControlled('controlled_prod'),
} + {
    'uncontrolled/pub_dev.json': configUncontrolled('uncontrolled_dev'),
}