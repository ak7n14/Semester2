#include <math.h>
#include <stdio.h>

typedef int (*funT)(int);
int *mapApply(int n, funT fs[], int size);
int func1(int);
int func2(int);
int func3(int);
int func4(int);
int func5(int);

int main() {
    
    funT array[5] = { func1, func2, func3, func4, func5 };
    mapApply(2, array, 5 );
    
    return 0;
}

int func1 (int n) {
    return n + 1;
}

int func2 (int n) {
    return n + 2;
}

int func3 (int n) {
    return n + 3;
}

int func4 (int n) {
    return n + 4;
}

int func5 (int n) {
    return n + 5;
}

int *mapApply(int n, funT fs[], int size) {
    int *result = malloc(sizeof(int)*size);
    for (int i = 0 ; i != 5 ; i++) {
        result[i] = fs[i](n);
    }
    printf("result:%d\n",result);
    return result;
}