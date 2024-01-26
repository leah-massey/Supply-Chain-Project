
###  ✏️ User story 1:
As an admin at a top-level customer org
I want to view a list of my direct suppliers

#### - API docs:

GET /suppliers?type=direct

HTTP 200

Example Response:
```
["ZC123", "ZC234"]
```

#### Notes:
If there are no direct suppliers, an empty list will be returned.
If the user does not exist, an empty list will be returned.

###  ✏️ User story 2:

As an admin at a top-level customer org
I want to get the details of a direct supplier that I specify by ID

#### - API docs:

GET /suppliers?type=direct&supplierId={suppliedId}

Example Response:
```
{
"supplierId": "ZS123",
"supplierName": "Potato King"
"customers": [
    "ZS222", "ZS333"
    ]
}
```
#### Notes: 
If there is not supplier with matching id, an empty supplier object is returned

#### - Assumptions:
The userId is stored as in the Request Header under "User-Id"