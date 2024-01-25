Story:
As an admin, at a top level customer organisation, I want to view a list of my direct suppliers.

Spec:
Given a user [U] is present in a top level customer organisation [O]
And a organisation [O] has a  list of direct suppliers [DirSup]
When [U] requests a list of direct suppliers
Then [DirSup] is returned

### Tests for Domain
Test: [O] only has one direct supplier in [DirSup]
- Somehow create scenario that : when domain tries to look up company for ZU123 it gets back company ZC789
- Somehow create scenario that : When domain tries to look up a list of suppliers for ZC789 it gets back a supply chain that includes direct suppliers (ZS456) (ie. listOf("ZS456"))
- When a user queries the domain for the direct suppliers
- Then assert that reply [ZS456] (ie.listOf("ZS456"))

Test: [O] has multiple direct suppliers in [DirSup] 
- Somehow create scenario that : when domain tries to look up company for ZU122 it gets back ZC788
- Somehow create scenario that : When domain tries to look up suppliers for ZC788 it gets back a supply chain that includes direct suppliers (ZS455, ZS456)
- When a user queries the domain for the direct suppliers
- Then assert that reply [ZS455, ZS456]

Test: [O] has both direct and indirect suppliers 
- Somehow create scenario that : when domain tries to look up company for ZU122 it gets back ZC788
- Somehow create scenario that : When domain tries to look up suppliers for ZC788 it gets back a supply chain that includes direct suppliers (ZS455, ZS456) and indirect suppliers (ZS457)
- When a user queries the domain for the direct suppliers
- Then assert that reply [ZS455, ZS456]

Test: [O] has no direct suppliers but some indirect
- Somehow create scenario that : when domain tries to look up company for ZU123 it gets back ZC789
- Somehow create scenario that : When domain tries to look up suppliers for ZC789 it gets back a supply chain that includes no direct suppliers
- When a user queries the domain for the direct suppliers
- Then assert that reply []

### Tests for UserRepoJson
Test: [O] to which [U] belongs is returned as a string
- When the domain queries the userRepo for the [O] of "ZC321"
- Then assert that reply "ZU456"

Test: an unrecognised user returns null
- When the domain queries the userRepo with an invalid [U] "ZU450"
- Then assert that reply is null 

### Tests for SupplyChainRepoJson
Test: the supply chain belonging to [O] is returned
- When the domain queries the supplyChainRepo with [O] "ZC789"
- Then assert that reply is SupplyChain(directSuppliers = listOf("ZS111"), indirectSuppliers = listOf("ZS222", "ZS333"))


Test: an empty list 
Test 






