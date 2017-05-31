import getopt
import sys
import json
import numpy as np
import os
from sklearn.externals import joblib
from sklearn import neighbors
from sklearn import tree
from sklearn.naive_bayes import GaussianNB

# feature:
# day week month year
# MD5 TIME DES TIME RSA TIME AES TIME
type = {'1': '11111100','2':'00110000','3':'11000000','4':'00001100'}

# label:
# dislike<- 1-2-3-4-5 ->like

# os.chdir("E:\Code")

# train_feature = []
# train_label = []
# baseFreq = 0.25
# clf = neighbors.KNeighborsClassifier()
# for i in range(1, 99):
#     feature_list = []
#     val = []
#     x = 1.0 - 0.01 * i
#     for j in range(0, 4):
#         val.append(x)
#     val.extend(type[i % 4])
#     for j in val:
#         feature_list.append(float(j))
#     if feature_list[0] >= 2 * baseFreq or feature_list[1] >= 1.8 * baseFreq or feature_list[
#         2] >= 1.6 * baseFreq or \
#                     feature_list[3] >= 1.4 * baseFreq:
#         train_label.append(5)
#     elif feature_list[0] >= 1.8 * baseFreq or feature_list[1] >= 1.6 * baseFreq or feature_list[
#         2] >= 1.4 * baseFreq or feature_list[3] >= 1.2 * baseFreq:
#         train_label.append(4)
#     elif feature_list[3] < baseFreq * 0.2 or feature_list[2] < baseFreq * 0.4 or feature_list[
#         1] < baseFreq * 0.6:
#         train_label.append(1)
#     elif feature_list[3] < baseFreq * 0.4 or feature_list[2] < baseFreq * 0.6 or feature_list[
#         1] < baseFreq * 0.8:
#         train_label.append(2)
#     else:
#         train_label.append(3)
#     train_feature.append(feature_list)
# train_feature = np.array(train_feature)
# train_label = np.array(train_label)
# clf.fit(train_feature, train_label)
# print("Finsh")
# joblib.dump(clf, OWNER + "_FavoriteTypeKNN.m")

ISTRAIN="false"
ISFIRST="false"
os.chdir("E:\Code")
options, args = getopt.getopt(sys.argv[1:], "", ["json=", "owner=", "isTrain=", "label=","isFirst="])
if options != []:
    for name, value in options:
        if name == '--json':
            JSON = value
        if name == '--owner':
            OWNER = value
        if name == '--isTrain':
            ISTRAIN = value
        if name == '--label':
            LABEL = value.split(",")
        if name=='--isFirst':
            ISFIRST=value

JSON = JSON.replace("'", "\"")
freqMap = json.loads(JSON)
keys = freqMap.keys()

res={}
index = 0
for i in keys:
    val = freqMap[i]
    baseFreq = float(1.0 / len(val))
    val.extend(type[i])
    feature_list = []
    for j in val:
        feature_list.append(float(j))
    if ISTRAIN == "true":
        print(baseFreq)
        if 'LABEL' in dir():
            train_label = np.array([int(LABEL[index])])
        else:
            if feature_list[0] >= 2 * baseFreq or feature_list[1] >= 1.8 * baseFreq or feature_list[
                2] >= 1.6 * baseFreq or \
                            feature_list[3] >= 1.4 * baseFreq:
                train_label = np.array([5])
            elif feature_list[0] >= 1.8 * baseFreq or feature_list[1] >= 1.6 * baseFreq or feature_list[
                2] >= 1.4 * baseFreq or feature_list[3] >= 1.2 * baseFreq:
                train_label = np.array([4])
            elif feature_list[3] < baseFreq * 0.2 or feature_list[2] < baseFreq * 0.4 or feature_list[
                1] < baseFreq * 0.6:
                train_label = np.array([1])
            elif feature_list[3] < baseFreq * 0.4 or feature_list[2] < baseFreq * 0.6 or feature_list[
                1] < baseFreq * 0.8:
                train_label = np.array([2])
            else:
                train_label = np.array([3])
        train_feature = np.array([feature_list])
        if ISFIRST=="true":
            clf=neighbors.KNeighborsClassifier()
        else:
            clf = joblib.load(OWNER + "_FavoriteTypeKNN.m")
        clf.fit(train_feature, train_label)
        joblib.dump(clf, OWNER + "_FavoriteTypeKNN.m")
        print(train_feature)
        print(train_label)
    else:
        test_feature = np.array([feature_list])
        os.chdir("E:\Code")
        clf = joblib.load(OWNER + "_FavoriteTypeKNN.m")
        res[i]=int(list(clf.predict(test_feature))[0])
    index += 1
print(json.dumps(res))

