## User Story 1:
As an admin, at a top level customer organisation, I want to view a list of my direct suppliers.

Spec:
Given a user [U] is present in a top level customer organisation [O]
And an organisation [O] has a list of direct suppliers [DirSup]
When [U] requests a list of direct suppliers
Then [DirSup] is returned

### Tests for Domain
Test: [O] only has one direct supplier in [DirSup]
- Fake a scenario where : when domain tries to look up organisation for ZU123 it gets back organisation ZC789
- Fake a scenario where : When domain tries to look up a list of suppliers for ZC789 it gets back a supply chain that includes direct suppliers (ZS456) (ie. listOf("ZS456"))
- When a user queries the domain for the direct suppliers
- Then assert that reply is [ZS456] 

Test: [O] has multiple direct suppliers in [DirSup] 
- Fake a scenario where : when domain tries to look up organisation for ZU122 it gets back ZC788
- Fake a scenario where : When domain tries to look up suppliers for ZC788 it gets back a supply chain that includes direct suppliers (ZS455, ZS456)
- When a user queries the domain for the direct suppliers
- Then assert that reply is [ZS455, ZS456]

Test: [O] has both direct and indirect suppliers 
- Fake a scenario where : when domain tries to look up organisation for ZU122 it gets back ZC788
- Fake a scenario where : When domain tries to look up suppliers for ZC788 it gets back a supply chain that includes direct suppliers (ZS455, ZS456) and indirect suppliers (ZS457)
- When a user queries the domain for the direct suppliers
- Then assert that reply is [ZS455, ZS456]

Test: [O] has no direct suppliers but some indirect
- Fake a scenario where : when domain tries to look up organisation for ZU123 it gets back ZC789
- Fake a scenario where : When domain tries to look up suppliers for ZC789 it gets back a supply chain that includes no direct suppliers
- When a user queries the domain for the direct suppliers
- Then assert that reply is []

Test: [O] has no suppliers at all 
- Fake a scenario where : when domain tries to look up organisation for ZU123 it gets back ZC789
- Fake a scenario where : When domain tries to look up suppliers for ZC789 it gets back a supply chain that is empty
- When a user queries the domain for the direct suppliers
- Then assert that reply is []


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







