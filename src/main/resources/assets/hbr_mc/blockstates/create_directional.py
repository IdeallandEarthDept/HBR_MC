import json

# 定义模板
template = {
    "variants": {
        "facing=north": { "model": "" },
        "facing=south": { "model": "", "y": 180 },
        "facing=west":  { "model": "", "y": 270 },
        "facing=east":  { "model": "", "y": 90 }
    }
}

# 方块注册名列表
block_names = [
    "inst_microphone_seated",
    "inst_microphone_in_hand",
    "inst_st_guitar",
    "inst_bed_double",
    "inst_microphone",
    "inst_drum_set",
    "inst_wood_guitar",
    "inst_bass",
    "inst_keyboard",
    "inst_speaker"
]

# 生成对应的 JSON 文件
for block_name in block_names:
    # 创建一个副本
    block_json = template.copy()
    
    # 修改每个模型的名称
    for key in block_json["variants"]:
        block_json["variants"][key]["model"] = f"hbr_mc:{block_name}"
    
    # 保存到文件
    with open(f"{block_name}.json", "w") as file:
        json.dump(block_json, file, indent=4)

print("所有方块状态文件已生成！")
