# Paint shop - Programming Challenge

This code is my Java solution for the Paint shop challenge.

# The solution

The solution adopted the mix starts with all colors as _GLOSS_. It's only possible to change the color to _MATTE_, in 
order to reduce the number of iterations over customers and colors.

To check if the customer is happy, 
* First of all check if he has a _MATTE_ option, and check if the mix has that color as _MATTE_.
* If the customer does not have _MATTE_ or is not happy with the _MATTE_ option, iterate over its colors and check one by one.

### The process:
1. All colors are _GLOSS_. 
2. Iterate over customers to check if they are happy with that solution. 
3. If a customer is not happy: 
    1. If it has a _MATTE_ option: 
        1. change that color to _MATTE_;
        2. Re-start the iteration over customers, to check if that change affected anybody.
    2. Otherwise, it means someone else required _MATTE_ to all colors of this customer. Therefore, no solution exists.
4. If the customer is happy, goes to the next customer.

### Example:
Input:
```text
2
1 G 2 G
1 M 2 G
2 M
```

1. The initial mix colors are: `[G G]`;
2. The first customer is happy;
3. The second customer is happy, as well;
4. The third customer is not happy and has a _MATTE_ option, so change the mix: `[G M]`;
5. Re-start the iteration over customers;
6. The first customer is happy;
7. The second customer is not happy and has a _MATTE_ option, change the mix: `[M M]`;
8. Re-start the iteration over customers;
9. The first customer is no longer happy and does not have a _MATTE_ option. So, **this mix has no solution**.

# How to use

The solution was implemented using _Java 8_ and _Maven_. 

### Build and Test the application

```
$ maven clean install
```

A `.jar` will be available in the `target` folder.

### Execute the .jar in command line
```
$ java -jar ./target/paintshop-1.0.jar <path_to_the_file>
```
