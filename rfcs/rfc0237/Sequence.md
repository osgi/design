





Example of AE turns light ON!



```sequence
participant AE Core
participant OSGi Registry
participant Protocol Binding 
participant CSE1
participant CSE2
participant Light   

Note Left of AE Core: registration \nof AE Core
AE Core->OSGi Registry: getService()
AE Core->Protocol Binding: create( AE )
Protocol Binding->CSE1: create( AE )
CSE1->Protocol Binding: created resource
Protocol Binding->AE Core: created resource
AE Core->OSGi Registry: update Service Property (AE-ID, PoA)

Note Left of AE Core: registration\n of Device
Light->CSE2: create(Light)
CSE2->Light: OK

Note Left of AE Core: create state \nas <FlexContainer>
Light->CSE2: create(Light/state)
CSE2->Light: OK

Note Left of AE Core: create subscription\n to subscribe change
Light->CSE2: create(Light/state/subscription)
CSE2->Light: OK

Note  Left of AE Core: search devices
AE Core->Protocol Binding: discovery('/CSE2/Light')
Protocol Binding->CSE1: discovery()
CSE1->CSE2: forward discovery()
CSE2->CSE1: discovery result
CSE1->Protocol Binding:discovery result
Protocol Binding->AE Core:discovery result

Note  Left of AE Core: Turn ON the Light

AE Core->Protocol Binding: update('/CSE2/Light/status = true')
Protocol Binding->CSE1: update()
CSE1->CSE2: forward update()
CSE2->Light: Notification\n state changed to ON
Note Right of Light: Turn ON!
CSE2->CSE1: OK
CSE1->Protocol Binding:OK
Protocol Binding->AE Core:OK





```



