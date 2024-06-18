1230.#include <iostream>
#include <stdio.h>
const int poolMAX = 10000+100;
int poolCnt = 0;
int N, M;
int data, x, y;

struct Node
{
    Node()
    : next(nullptr), data(0)
    {};
    
    Node* next;
    int data;
};

Node* head = nullptr;
Node pool[poolMAX];
Node* getNode(int _data)
{
    pool[poolCnt].data = _data;
    pool[poolCnt].next = nullptr;
    return &pool[poolCnt++];
}

//n번째 노드를 찾는 함수
Node* findNode(Node* ptr, int n)
{
    for(int i=1; i<n; ++i)
        ptr = ptr->next;
    
    return ptr;
}

void Insert()
{
    std::cin >> x >> y;
    auto iter = findNode(head, x);

    for(int i=0; i<y; ++i)
    {
        std::cin >> data;
        auto newNode = getNode(data);
        
        if(i==0 && x==0)
        {
            newNode->next = head;
            head = newNode;
        }
        else
        {
            newNode->next = iter->next;
            iter->next = newNode;
        }
        iter = newNode;
    }
}

void Delete()
{
    std::cin >> x >> y;
    
    auto iter = findNode(head, x);
    
    for(int i=0; i<y; ++i)
        iter->next = iter->next->next;
}

void Add()
{
    std::cin >> y;
    Node* iter = head;
    while(iter->next) iter = iter->next;
  
    for(int i=0; i<y; ++i)
    {
        std::cin >> data;

        Node* newNode = getNode(data);
        iter->next = newNode;
        iter = newNode;
    }
}

int main()
{
    int T = 10;
    for(int tc = 1; tc<=T; ++tc)
    {
        char op;
        Node* ptr = nullptr;

        head = nullptr;
        poolCnt = 0;
        
        std::cin >> N;
        for(int i=0; i<N; ++i)
        {
            std::cin >> data;
            
            auto newNode = getNode(data);
            if(head == nullptr)
                head = newNode;
            else
                ptr->next = newNode;
            ptr = newNode;
        }
        
        std::cin >> M;
        for(int i=0; i<M; ++i)
        {
            std::cin >> op;
            switch(op)
            {
                case 'I':
                    Insert();
                    break;
                    
                case 'D':
                    Delete();
                    break;
                    
                case 'A':
                    Add();
                    break;
            }
        }
        
        std::cout << '#' << tc << ' ';
        for(int i=0; i<10; ++i)
        {
            std::cout << head->data << ' ';
            head = head->next;
        }
        std::cout << '\n';
    }
    return 0;
    
}
