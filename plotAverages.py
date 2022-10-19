import sys
import os

# python plotAverages.py <experiment dir> <log prefix> <log suffix>

n = len(sys.argv)
if n != 4:
    print("Specify a root experiment directory and log file prefix and suffix, as in: ")
    print("python plotAverages.py mariopdsl MarioPDSL- _MAPElites_log.txt")
    exit(1)

directory = sys.argv[1]
log_prefix = sys.argv[2]
log_suffix = sys.argv[3]
entries = os.listdir(directory)
# For example: FullDirect2GAN0, FullDirect2GAN1, ..., FullDirect2GAN9, RestrictedDirect2GAN0, RestrictedDirect2GAN1, ..., RestrictedDirect2GAN9

all_data = dict()

for dir in entries:
    name_len = len(dir)
    index = name_len - 1
    while dir[index].isnumeric():
        index -= 1

    # Index is last index before the numeric portion
    prefix = dir[0:(index+1)]
    experiment_num = prefix = int(dir[(index+1):name_len])
    if not prefix in all_data:
        all_data[prefix] = dict()

    all_data[prefix][experiment_num] = dict()

    complete_file_path = f"{directory}/{dir}/{log_prefix}{dir}{log_suffix}"

    with open(complete_file_path) as log:
        print(log)
        # TODO!
