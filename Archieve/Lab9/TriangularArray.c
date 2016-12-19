#include<stdio.h>

struct TRIARR{
    int row;
    int col;
    int arr[6][6];
};

typedef struct TRIARR as;
void triStore(struct TRIARR as, int N, int M, int row, int col, int val);
int triFetch(struct TRIARR as, int N, int M, int row, int col);

int main(){
    struct TRIARR as;
    as.row = 6;
    as.col = 6;
    triStore(as, 10, 5, 3, 6, 6);
    triFetch(as, 3, 3, 6, 6);
    
}

void triStore(struct TRIARR as, int N, int M, int row, int col, int val){
    if((row<N) && (col<M) && (col>=row)){
        printf("Added %d to location %d,%d\n",val,N,M);
        as.arr[row][col] = val;
    }
    else{
        printf("Invalid Location\n");
    }
}

int triFetch(struct TRIARR as, int N, int M, int row, int col){
    if((row<N) && (col<M) && (col>=row)){
        printf("%d \n",as.arr[row][col]);
        return as.arr[row][col];
    }else{
        printf("Invalid location\n");
        return 0;
    }
}
