###To run application:

        mvn clean
        mvn spring-boot:run

###To use:

In ApplicationConfiguration.class comment out (will not work on localhost, as session is not stored):
    @Scope(value= WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)

1. Enter card details (or copy-paste from CSV) in the following format tp text area:

        Bank Name, 6712-6271-2323-3455, Nov-2012
        Bank Name2, 6712-6271-2323-999, Dec-2013

2. Click "Submit Cards". Cards will be added to existing list.

3. Click "Fetch Cards". Json object in sorted order will be rendered on screen.


####Notes:
Application uses List to store Credit Cards. Each time we sort it before retrieving from credit card service.
To avoid sorting List can be replaced with TreeSet.


