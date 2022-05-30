# ResidenceAddon
This is an addon for the SimplePets v5 plugin.

### Requirements:
- [Residence](https://www.spigotmc.org/resources/11480/) Plugin `(At least v5.0.1.6)`

### Default configuration (Located in `plugins/SimplePets/Addons/config/Residence.yml`)
```yaml
# What should the bypass permission be set to?
bypass-permission: pet.residence.bypass

checks:
  spawning:
    flag:
        # What should the flag for spawning a pet in a residence be named?
        name: 'allow-pet-spawn'
        # What should the default value of the flag be?
        default-value: true
        
    # Should the addon check when a pet is spawned?
    enabled: true
    # This message is only visible when you hover over the 'pet failed to spawn' message
    # You can make this blank or 'null' if you want no message
    reason: '&cYour pet is not able to be spawned in this area'
  mounting:
    flag:
        # What should the flag for mounting a pet in a residence be named?
        name: 'allow-pet-mounting'
        # What should the default value of the flag be?
        default-value: true
        
    # Should the addon check when the player mounts the pet?
    enabled: true
    # Should the pet be removed or should it just be canceled?
    remove-pet: true
  moving:
    flag:
        # What should the flag for when a pet is walking in a residence be named?
        name: 'allow-pet-enter'
        # What should the default value of the flag be?
        default-value: true
        
    # Should the addon check when a pet moves?
    enabled: true
    # Should the pet be removed or should it just be canceled?
    remove-pet: true
  riding:
    flag:
        # What should the flag for is riding their pet in a residence be named?
        name: 'allow-pet-riding'
        # What should the default value of the flag be?
        default-value: true
        
    # Should the addon check when the pets owner is riding it?
    enabled: true
    # Should the pet be removed or should it just be canceled?
    remove-pet: true
    # Should the player be dismounted (if remove-pet is set to true this is ignored)?
    dismount: true
```