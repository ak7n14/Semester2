#include <stdio.h>
#include <math.h>
#include <fenv.h>
#include <limits.h>

int analyse(int *wholePtr , double *fracPtr, double d);

int main(){
    double number = -10.33;
    int whole;
    double frac;
    analyse(&whole,&frac,number);
    return 0;
}

int analyse(int *wholePtr , double *fracPtr, double d){
    int whole = *wholePtr;
    double frac = *fracPtr;
    if(signbit(d)==0){
        printf("sign part: %s\n", "+");
    }
    else{
        printf("sign part:%s\n","-");
    }
    
    fesetround(FE_DOWNWARD);
    whole = rint(d);
    printf("Whole part: %d\n  ", whole);
    frac = d - whole;
    printf("fraction part: %f\n  ", frac);
    
    return 0;
}