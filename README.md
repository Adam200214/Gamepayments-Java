# Gamepayments-Java
A Java API for the GamePayments service. This utilizes other APIs like Gson, Guice, and the Apache HTTP Client.

## Create a GamePayments Object
Create a GamePayments object. This is used to create claim requests and various other useful requests.

```java
GamePayments payments = new GamePayments.Builder("api-key").build();
```

## Process requests
Call the run method of the appropriate service(s) you want to make use of in
 your game logic update loop. This will allow claim requests to be executed on the
 same game thread you process logic.

```java
GamePayments payments = new GamePayments.Builder("api-key").build();

payments.getClaimService().run();
```

## Add Listeners
You can add listeners that are called from the ClaimService#run method when a request has been
timed out, failed due to an error, or completed successfully. 

```java
GamePayments payments = new GamePayments.Builder("api-key").build();

payments.getClaimService().addOkListener((request, result) -> {
    if (!result.getTransactions().isEmpty()) {
        System.out.println("You have claimables!");
    }
});
```

## Submit Requests
To submit a request to claim, simply call the ClaimService#submit function.
```java
GamePayments payments = new GamePayments.Builder("api-key").build();

String apiKey = "foo";

String username = "bar";

payments.getClaimService().submit(apiKey, username);
```


