from anytree import Node, RenderTree #tài liệu anytree: https://anytree.readthedocs.io/en/latest/
import asyncio
import time
from memory_profiler import profile

#function to count the number of "1" in binary of input
def numberOfSetBits(i):
    i = i - ((i >> 1) & 0x55555555)
    i = (i & 0x33333333) + ((i >> 2) & 0x33333333)
    return (((i + (i >> 4) & 0xF0F0F0F) * 0x1010101) & 0xffffffff) >> 24

def prunc(S):
    if (S not in tree):
        tree.append(S)
        return False
    else:
        return True

def extendSequence(Sx, Sy):
    supSx = Sx.name[1] #support of Sx
    supSy = Sy.name[1] #support of Sy
    itemSxSy = Sx.name[0] + Sy.name[0] #merge item name
    supSxSy = supSx & supSy
    node = (itemSxSy, supSxSy)
    return Node(node, parent=None)



# def extendItem(Sx, Sy):
#     itemSxSy = Sx.name[0] + Sy.name[0]  # merge item name
#     countsup = ''
#     for item in pure_DB:
#         index1 = item.find(Sx.name[0])
#         if (Sx.name[0] == Sy.name[0]):
#             index2 = item.find(Sy.name[0], index1+1)
#         else:
#             index2 = item.find(Sy.name[0])
#
#         if (index1 != -1) and (index2 != -1):
#             countsup = countsup + '1'
#         else:
#             countsup = countsup + '0'
#     supSxSy2 = int(countsup,2)
#     print('item: ', itemSxSy, 'support: ', supSxSy2)
#     node = (itemSxSy, supSxSy2)
#     return Node(node, parent=None)


##################### FCSP_EXT PROGRAM ######################################
async def FCSP_Ext(p, minsup):
    # print("^^^ ",p)
    #ban đầu vẫn nhận root
    print('This is sub function with p = ', p)
    list = []
    for pre, fill, node in RenderTree(p):
        list.append(node)

    print("^^ ",list)

    for Sx in list:
        if (not prunc(Sx)):
            for Sy in list:
                if (Sy.name is not None) and (Sx.name is not None):
                    # print("Vào extend item")
                    # extendCheck = False;
                    # tempNode = extendItem(Sx, Sy)
                    # support_of_item = tempNode.name[1]
                    #
                    # if (numberOfSetBits(support_of_item) >= minsup):
                    #     tempNode.parent = Sx;
                    #     extendCheck = True;
                    #     print('Add by extendItem', tempNode.name)
                    if (Sx.name[0] < Sy.name[0]):
                        #print("Vào extend sequence")
                        tempNode = extendSequence(Sx, Sy)
                        support_of_item = tempNode.name[1]
                        if (numberOfSetBits(support_of_item) >= minsup):
                            tempNode.parent = Sx;
                            # print("--> ",tempNode.name)
            #end for
            task2 = asyncio.create_task(FCSP_Ext(Sx, minsup))
            await(task2)



##################### pDBV_FCSP PROGRAM ######################################
@profile
async def pDBV_FCSP(DB, minsup):
    root = Node (None, None)  #Khởi tạo root với null
    F1 = []                   #Khởi tạo list kết quả rỗng
    for s in DB:
        # print("-% ",s)
        #o(s) = numberOfsetBits(s)
        support_of_item = s[1]
        if (numberOfSetBits(support_of_item) >= minsup):
            print(s[1], numberOfSetBits(support_of_item)) # này là để kiểm tra xem support đúng hay không, unlock thì nhớ comment lại
            F1.append(s)
    # print('--> ',F1)
    #thêm item vào F1
    F1 = sorted(F1, key = lambda x: (numberOfSetBits(x[1])), reverse=False )   #sắp xếp theo support a.k.a số bit 1

    print("--> ",F1)

    #thêm F1 vào child của root
    for i in F1:
        node1 = Node(i, parent=root)
        # print(node1)

    # print("^^^ ",root)

    for pre, fill, node in RenderTree(root):
        # print("^^^ ",root)
        # print("%s%s" % (pre, node.name))
        #create new task
        task1 = asyncio.create_task(FCSP_Ext(node, minsup))
        # print("--$ ",node, "^^^ ",root)
        await task1

    for pre, fill, node in RenderTree(root):
        print("---> %s%s" % (pre, node.name))



##################### MAIN PROGRAM ######################################
pure_DB = {"CAAC(CADEF)D", "AB(AE)CB", "(BC)CABC", "ABBCA(BCE)", "BA(BCE)D", "AB(ADE)A"}

# print(numberOfSetBits(1))

DBV = {('A',63), ('B',62), ('C', 31), ('D',49), ('E',59), ('F',1)}
minsup = 3 #minsup = 50%
tree = [] #tree to expand

start = time.time()
asyncio.run(pDBV_FCSP(DBV, minsup))
end = time.time()
print("The time of execution of above program is :",
      (end-start) * 10**3, "ms")

