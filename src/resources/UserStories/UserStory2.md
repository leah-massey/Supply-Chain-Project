## User Story 2:
As an admin at a top-level customer org
I want to get the details of a direct supplier that I specify by ID

Spec:
Given a user [U] is present in a top level customer organisation [O]
And [O] has a list of direct suppliers [DirSup]
Where each direct supplier [D] has an ID
When [U] requests a [D] from [O] with specified ID
Then [D] is returned

### Tests for Domain getDirectSupplierThatHasSpecifiedId Function

Test: a [D] present in [O], whose ID also matches the specified ID is returned
- Somehow create scenario that : when domain tries to look up company for ZU123 it gets back company ZC789
- Somehow create scenario that : When domain tries to look up a list of suppliers for ZC789 it gets back a SupplyChain that includes [D] (ZS456) 
- Somehow create scenario that: When domain tries to look up a list of [DirSup], it gets back a list that includes [D] (ZS456)-> [ZS456, ZS111, ZS456]
- When a user queries the domain for a direct supplier with a specified ID of [D] (ZS456)
- Then assert that reply: 
  {
  "supplierId": "ZS456",
  "supplierName": "Potato King",
  "customers": [
  "ZC123", "ZC321"
  ]
  }

Test: if no [D] in [O] has matching specified ID, an empty [D] is returned
- Somehow create scenario that : when domain tries to look up company for ZU123 it gets back company ZC789
- Somehow create scenario that : When domain tries to look up a list of suppliers for ZC789 it gets back a SupplyChain that does not include [D] (ZS456)
- Somehow create scenario that : When domain tries to look up a list of [DirSup] for ZC789 it gets back a list that does not include [D] (ZS456) -> [ZS456, ZS111]
- When a user queries the domain for a direct supplier with a specified ID (ZS456)
- Then assert that reply:
  {
  "supplierId": "",
  "supplierName": "",
  "customers": []
  }

Test: if no [D] in [O] has matching specified ID, but specified ID matches an indirect supplier in [O], an empty [D] is returned 
- Somehow create scenario that : when domain tries to look up company for ZU123 it gets back company ZC789
- Somehow create scenario that : When domain tries to look up a list of suppliers for ZC789 it gets back a SupplyChain that includes [D] (ZS456)
- Somehow create scenario that : When domain tries to look up a list of [DirSup] for ZC789 it gets back a list that does not include [D] -> [ZS456, ZS111]
- When a user queries the domain for a direct supplier with a specified ID (ZS456)
- Then assert that reply:
  {
  "supplierId": "",
  "supplierName": "",
  "customers": []
  }










